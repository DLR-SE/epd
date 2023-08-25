package test.de.emir.tuml.ucore.runtime.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.Environment;
import de.emir.model.universal.physics.ObjectLayer;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.impl.EnvironmentImpl;
import de.emir.model.universal.physics.impl.ObjectLayerImpl;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.spatial.SpatialDelegateProviders;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.serialization.AbstractSerializer;
import de.emir.tuml.ucore.runtime.serialization.bin.BinarySerializer;
import de.emir.tuml.ucore.runtime.serialization.bin.BinaryShortSerializer;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;

import static org.junit.Assert.*;

public class BinarySerializerTest {

	static {
		SpatialDelegateProviders.register();
	}
	public static void main(String[] args) {
		new BinarySerializerTest().testSmallModel();
	}

	private void testSmallModel() {
		//create simple model with an environment and one PhysicalObject
		Environment env = new EnvironmentImpl();
		ObjectLayer olayer = new ObjectLayerImpl();
		env.getLayer().add(olayer);
		PhysicalObject pobj = new PhysicalObjectImpl();
		pobj.getPose().set(new CoordinateImpl(54, 8, CRSUtils.WGS84_2D), new EulerImpl(0, 0, 89, AngleUnit.DEGREE));
		olayer.getObjects().add(pobj);
		
		checkModel(env);
		checkPerformance(env);
	}

	private void checkPerformance(UObject env) {
		int runs = 1000;
		long start_xml = System.currentTimeMillis();
		for (int i = 0; i < runs; i++)
			checkPerformance(env, XMLSerializer.class);
		long end_xml = System.currentTimeMillis();
		ULog.info("XML Serialisation took: " + (end_xml - start_xml) + " [ms] for " + runs + " iterations");
		
		
		long start_bin = System.currentTimeMillis();
		for (int i = 0; i < runs; i++)
			checkPerformance(env, BinarySerializer.class);
		long end_bin = System.currentTimeMillis();
		ULog.info("BINARY Serialisation took: " + (end_bin - start_bin) + " [ms] for " + runs + " iterations");
	}

	private <T extends AbstractSerializer> void checkPerformance(UObject env, Class<T> class1) {
		try {
			AbstractSerializer as_out = class1.newInstance();
			byte[] data = serialize(env, as_out);
			ULog.info("Serialize to: " + data.length + " bytes");
			
			AbstractSerializer as_in = class1.newInstance();
			UObject newObj = deserialize(data, as_in);			
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
	}

	private UObject deserialize(byte[] data, AbstractSerializer as_in) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		return as_in.deserialize(bais);
	}

	private byte[] serialize(UObject env, AbstractSerializer as) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		as.serialize(env, baos);
		return baos.toByteArray();
	}

	private void checkModel(UObject env) {
		BinarySerializer bs = new BinarySerializer();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			bs.serialize(env, baos);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Failed to serialize model: " + env);
		}
		
		byte[] bytes = baos.toByteArray();
		ULog.info("Need: " + bytes.length + " bytes for serialization");
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		UObject res = null;
		try {
			res = new BinarySerializer().deserialize(bais);//new instance, since we do NOT want to reuse the hashmaps
		}catch(Exception e) {
			e.printStackTrace();
			fail("Failed to read the serialized model");
		}
		assertTrue(UCoreUtils.equals(env, res));
		System.out.println();
	}
}
