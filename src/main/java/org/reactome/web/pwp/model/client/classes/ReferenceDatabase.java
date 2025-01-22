package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat (fabregat@ebi.ac.uk)
 */
@SuppressWarnings("UnusedDeclaration")
public class ReferenceDatabase extends DatabaseObject {

    private String accessUrl;
    private List<String> name;
    private String url;

    public ReferenceDatabase() {
        super(SchemaClass.REFERENCE_DATABASE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.accessUrl = DatabaseObjectUtils.getStringValue(jsonObject, "accessUrl");

        this.name = DatabaseObjectUtils.getStringList(jsonObject, "name");

        this.url = DatabaseObjectUtils.getStringValue(jsonObject, "url");
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public List<String> getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
