package de.emir.epd.alert.view.details;

public class DefaultDetailsProvider extends AlertDetailsProvider {

    public DefaultDetailsProvider(String id) {
        super(id);
    }

    @Override
    protected String getDetailedText() {
        return "Nothing";
    }
}
