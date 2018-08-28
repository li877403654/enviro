package dalgen

import pub.functions.PropsFuncs
import pub.functions.PropsFuncs2

def configFile = args? args[0]: null

def configurator = new Configurator(configFile);
def config = configurator.config;

//config.jdbcUrl = 'jdbc:mysql://127.0.0.1:33064/ykxw_dev?user=zx&password=Bigdata_1';
config.jdbcUrl = PropsFuncs2.getString("sys", "jdbc_url");
println config.jdbcUrl

//config.jdbcUrl = 'jdbc:mysql://172.16.108.101:3306/zx?user=zx&password=a';

//config.jdbcUrl = 'jdbc:mysql://mac:3306/zx?user=zx&password=a';
//config.jdbcUrl = 'jdbc:mysql://localhost:3306/zx?user=zx&password=a';

//config.jdbcUrl = 'jdbc:mysql://120.25.152.16:3306/envir?user=envir&password=Bigdata_1';

//config.jdbcUrl = 'jdbc:mysql://172.16.108.101:3306/mda?user=mda&password=a';

config.driverClass = 'com.mysql.jdbc.Driver';
config.useValueGenerator = true;

println ''
def schemaReader = new SchemaReader(config);
def tableInfos = schemaReader.read();

def codeWriter = new CodeWriter(config, tableInfos);
codeWriter.write();

println "codes generated in folder ${new File('output').absolutePath}"
println "\ndone."
