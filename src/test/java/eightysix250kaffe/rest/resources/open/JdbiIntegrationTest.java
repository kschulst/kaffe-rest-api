package eightysix250kaffe.rest.resources.open;

public abstract class JdbiIntegrationTest {
	 
//    private DBI dbi;
// 
//    private Handle handle;
// 
//    private Liquibase liquibase;
// 
//    protected abstract DatabaseConfiguration getDatabaseConfiguration();
// 
//    protected abstract void setUpDataAccessObjects();
// 
//    @Before
//    public void setUpDatabase() throws Exception {
//        IntegrationTestConfiguration configuration = new IntegrationTestConfiguration(getDatabaseConfiguration());
//        Environment environment = new Environment("test", configuration, null, null);
//        dbi = new DBIFactory().build(environment, configuration.getDatabaseConfiguration(), "test");
//        handle = dbi.open();
//        migrateDatabase();
//        setUpDataAccessObjects();
//    }
// 
//    @After
//    public void tearDown() throws Exception {
//        liquibase.dropAll();
//        handle.close();
//    }
// 
//    protected <SqlObjectType> SqlObjectType onDemandDao(Class<SqlObjectType> sqlObjectType) {
//        return dbi.onDemand(sqlObjectType);
//    }
// 
//    private void migrateDatabase() throws LiquibaseException {
//        liquibase = new Liquibase("migrations.xml", new ClassLoaderResourceAccessor(), new JdbcConnection(handle.getConnection()));
//        liquibase.update(null);
//    }
// 
//    private class IntegrationTestConfiguration extends Configuration {
// 
//        private DatabaseConfiguration databaseConfiguration;
// 
//        public IntegrationTestConfiguration(DatabaseConfiguration databaseConfiguration) {
//            this.databaseConfiguration = databaseConfiguration;
//        }
// 
//        public DatabaseConfiguration getDatabaseConfiguration() {
//            return databaseConfiguration;
//        }
//    }
}