package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectImages;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat (fabregat@ebi.ac.uk)
 */
public class ReferenceDNASequence extends ReferenceSequence {

    public ReferenceDNASequence() {
        super(SchemaClass.REFERENCE_DNA_SEQUENCE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.referenceDNASequence();
    }
}
