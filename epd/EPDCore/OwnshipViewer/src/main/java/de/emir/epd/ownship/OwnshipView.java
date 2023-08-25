package de.emir.epd.ownship;

import de.emir.epd.ais.AisTargetView;
import de.emir.epd.ais.manager.AisTargetManager;
import de.emir.epd.ais.model.IAisReadAdapter;
import de.emir.epd.model.EPDModel;
import de.emir.epd.model.EPDModelUtils;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.universal.physics.Environment;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class OwnshipView extends AisTargetView {


    public OwnshipView(String id) {
        super(id);

    }

    @Override
    protected void addListeners() {
        IAisReadAdapter mra = ServiceManager.get(AisTargetManager.class).getModelReadAdapter();

        if (mra == null) {
            return;
        }

        mra.subscribeChanged(oo -> {
			updateCurrentTarget();
        });

        updateCurrentTarget();
    }

    @Override
    protected void updateCurrentTarget() {
        Object o = PlatformUtil.getModelManager().getModelProvider().getModel();
        if(o instanceof EPDModel) {

            EPDModel model = (EPDModel) o;

            Environment env = model.getEnvironment();

            if (env != null){
                Vessel ownship = EPDModelUtils.getOwnship(env);

                if (ownship != null || ownship != currentTarget) {

                    if (currentTarget != null && targetTreeListener != null) {
                        try {
                            currentTarget.removeTreeListener(targetTreeListener);
                        } catch (Exception e) {
                            ULog.error(e.getMessage());
                        }
                    }

                    currentTarget = ownship;

                    if (currentTarget != null && targetTreeListener != null){
                        currentTarget.registerTreeListener(targetTreeListener);
                    }
                }
            }
        };

        updateInformation();
    }
}
