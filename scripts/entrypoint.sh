#!/bin/bash

# Wait for SQL Server to start
sleep 30s

# Run the initialization script
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P 'khoa123456' -d master -i /scripts/init-db.sql
