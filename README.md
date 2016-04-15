# SuperMarketDB
Supermarket DBMS project with SQLite  
  
Add initial Employee values from adb shell with root permissions.  
Commands:  
  adb shell  
  su    //root required  
  cd data/data/com.sande.SuperMarketDB/databases  
  sqlite3 SUPERMARKET  
  
This opens up the database in sqlite3. Run DML commands and restart the app.  
Should work now.  
Empty fields not handled.  
