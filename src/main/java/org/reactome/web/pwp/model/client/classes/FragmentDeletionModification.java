package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat (fabregat@ebi.ac.uk)
 */
public class FragmentDeletionModification extends FragmentModification {

    public FragmentDeletionModification() {
        super(SchemaClass.FRAGMENT_DELETION_MODIFICATION);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);
    }
}
