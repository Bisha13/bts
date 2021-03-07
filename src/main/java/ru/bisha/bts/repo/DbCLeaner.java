package ru.bisha.bts.repo;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class DbCLeaner {

    public static final String REPOPULATE_SQL = "repopulate.sql";

    @Autowired
    private DataSource dataSource;

    public void deleteAllData() {
        FileSystemResource a = new FileSystemResource(REPOPULATE_SQL);
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScripts(a);
        resourceDatabasePopulator.execute(dataSource);
    }

}
