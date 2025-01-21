package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat (fabregat@ebi.ac.uk)
 */
public class NegativeRegulation extends Regulation {

    NegativeRegulation(SchemaClass schemaClass){
        super(schemaClass);
    }

    public NegativeRegulation() {
        super(SchemaClass.NEGATIVE_REGULATION);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);
    }
}
