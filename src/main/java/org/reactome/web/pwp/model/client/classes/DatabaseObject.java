package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.client.common.ContentClientHandler;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectFactory;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectImages;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * DatabaseObject contains the minimum fields used to define an instance in the REACTOME RESTFul service
 *
 * @author Antonio Fabregat (fabregat@ebi.ac.uk)
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class DatabaseObject {
    private Long dbId;
    private String stId;
    private String stIdVersion;
    private String displayName;
    private SchemaClass schemaClass;
    private String className; //A easier-to-understand name
    private InstanceEdit created;
    private InstanceEdit modified;

    //DO NOT CHANGE THE VALUE IN THIS OBJECT
    public boolean isLoaded = false;

    public DatabaseObject(SchemaClass schemaClass) {
        this.schemaClass = schemaClass;
    }

    @SuppressWarnings("unchecked")
    public <T extends DatabaseObject> T cast() {
        return (T) this;
    }

    public void load(final ContentClientHandler.ObjectLoaded handler) {
        if (!this.isLoaded) {
            DatabaseObjectFactory.load(this, handler);
        } else {
            Scheduler.get().scheduleDeferred(() -> handler.onObjectLoaded(DatabaseObject.this));
        }
    }

    public void load(JSONObject jsonObject) {
        this.dbId = DatabaseObjectUtils.getLongValue(jsonObject, "dbId");
        DatabaseObjectFactory.cache.put(dbId + "", this); //Always cache the DB_ID

        this.stId = DatabaseObjectUtils.getStringValue(jsonObject, "stId");
        //Not all DatabaseObjects have ST_ID
        if (this.stId != null) DatabaseObjectFactory.cache.put(stId + "", this);

        this.stIdVersion = DatabaseObjectUtils.getStringValue(jsonObject, "stIdVersion");

        this.displayName = DatabaseObjectUtils.getStringValue(jsonObject, "displayName");

        this.className = DatabaseObjectUtils.getStringValue(jsonObject, "className");

        setDatabaseObject(jsonObject.get("created"), () ->
                created = DatabaseObjectUtils.getDatabaseObject(jsonObject, "created")
        );

        setDatabaseObject(jsonObject.get("modified"), () ->
                modified = DatabaseObjectUtils.getDatabaseObject(jsonObject, "modified")
        );

        checkDatabaseObject(DatabaseObjectUtils.getSchemaClass(jsonObject));
    }

    public Long getDbId() {
        return dbId;
    }

    public String getStId() {
        return stId;
    }

    public String getStIdVersion() {
        return stIdVersion;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getClassName() {
        return className;
    }

    public String getReactomeIdentifier() {
        return stId != null ? stId : "" + dbId;
    }

    public SchemaClass getSchemaClass() {
        return schemaClass;
    }

    public InstanceEdit getCreated() {
        return created;
    }

    public InstanceEdit getModified() {
        return modified;
    }

    private void checkDatabaseObject(SchemaClass schemaClass) {
        if (this.dbId.equals(0L) || this.displayName == null) {
            String msg = "WRONG DATABASE OBJECT [" + this.toString() + "].";
            throw new RuntimeException("WRONG DATABASE OBJECT [" + this.toString() + "].");
        }

        if (!this.schemaClass.equals(schemaClass)) {
            String msg = "WRONG SCHEMA CLASS. Expecting [" + schemaClass.schemaClass + "], found [" + this.schemaClass.schemaClass + "].";
            throw new RuntimeException(msg);
        }
    }

    //Override this method for those objects with associated icon
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.exclamation();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DatabaseObject that = (DatabaseObject) o;

        return !(dbId != null ? !dbId.equals(that.dbId) : that.dbId != null);

    }

    @Override
    public int hashCode() {
        return dbId != null ? dbId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return schemaClass.schemaClass + "{" +
                (stId != null ? "st_id=" + stId : "dbId=" + dbId) +
                ", displayName='" + displayName + '\'' +
                '}';
    }

    void setDatabaseObject(JSONValue jsonValue, Scheduler.ScheduledCommand command) {
        if (jsonValue == null) return;
        if (jsonValue.isNumber() == null) {
            command.execute();
        } else {
            DatabaseObjectFactory.cmds.add(command);
        }
    }
}