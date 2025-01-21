package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat (fabregat@ebi.ac.uk)
 */
@SuppressWarnings("UnusedDeclaration")
public class Url extends Publication {

    private String uniformResourceLocator;

    public Url() {
        super(SchemaClass.URL);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.uniformResourceLocator = DatabaseObjectUtils.getStringValue(jsonObject, "uniformResourceLocator");
    }

    public String getUniformResourceLocator() {
        return uniformResourceLocator;
    }
}
