package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat (fabregat@ebi.ac.uk)
 */
@SuppressWarnings("unused")
public class FunctionalStatusType extends DatabaseObject {

    private String definition;
    private List<String> name;

    public FunctionalStatusType() {
        super(SchemaClass.FUNCTIONAL_STATUS_TYPE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.definition = DatabaseObjectUtils.getStringValue(jsonObject, "definition");

        this.name = DatabaseObjectUtils.getStringList(jsonObject, "name");
    }

    public String getDefinition() {
        return definition;
    }

    public List<String> getName() {
        return name;
    }
}
