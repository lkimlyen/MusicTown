package com.demo.architect.data.repository.base.local;

import android.content.Context;

import com.demo.architect.data.helper.RealmHelper;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class DatabaseRealm {
    private Context context;

    public DatabaseRealm() {

    }

    public DatabaseRealm(Context context) {
        this.context = context;
    }

    public Realm getRealmInstance() {
        if (!RealmHelper.getInstance().getInitRealm()) {
            String nameDatabase ="abc.realm" ;
            RealmConfiguration realmConfigurationMain = new RealmConfiguration.Builder()
                    .name(nameDatabase)
                    .schemaVersion(1)
                    .migration(new MyMigration())
                    .build();
            Realm.setDefaultConfiguration(realmConfigurationMain);
            RealmHelper.getInstance().initRealm(true);
        }
        return Realm.getDefaultInstance();
    }



    //thay đổi database
    public class MyMigration implements RealmMigration {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

            RealmSchema schema = realm.getSchema();

        }

    }


}
