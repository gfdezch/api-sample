QUERY="IF NOT EXISTS ( SELECT TOP(1) [name] FROM [sys].[databases] WHERE [name] = '$DB_SCHEMA' ) CREATE DATABASE $DB_SCHEMA;";
echo "Init db start.";
/opt/mssql-tools/bin/sqlcmd -S sqlserver -U sa -P ${SA_PASSWORD} -q "${QUERY}"
echo "Init db done.";