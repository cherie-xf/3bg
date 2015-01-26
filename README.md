3qnow README
======

##Source Code Formatter

The source code formatter is defined in /docs/code_formatter.xml. Please import the file through your project properties in the Eclipse, like Properties->Java Code Style->Formatter->Import. 

When you modify or/and create any Java files, please make sure you do Ctrl+Shift+F to format the file before commiting the changes.

If you are not happy with the pre-defined format, you could edit the format in Eclipse and export to /docs/code_formatter.xml. And let all the team members know along with your reasonings of the changes.

##Demo Deployment

The master branch is deployed to: <br/>
Admin: http://54.149.114.253/admin/ or http://demo.3qnow.net/admin/ <br />
Web: http://54.149.114.253/web/ or http://demo.3qnow.net/web/ <br />

##Database

We have created several MYSQL scripts for different purposes.

<b>bootstrap.sql</b> - creates database and tables

<b>testdata.sql</b> - some test data for development use

<b>bootstrap_without_constrain.sql</b> - creates database and tables, but without constrains. This will be used only in development phase and should NOT be used for production.

<b>createuser.sql</b> - creates database admin user and grants right. It is usually used only once.

<b>droptables.sql</b> - drop tables. It is used for local development and remote testing servers.

##Database backup
Our database is backed up daily by cron job. For example, in the staging database server, the crob job backs up the database dump on the same server and also forwards the dump file to staging web server to avoid one of the server is terminated accidently.

##Database migration

Database migration framework is located in the commons/src/main/database/migrations.

Benefits
The database changes are versioned, which means we could easily deploy and roll back. Dropping database tables to recreate tables is not encouraged.

Instructions
Step 1: Download attached mybatis-migrations-3.2.0.zip or download directly from https://github.com/mybatis/migrations/releases/download/mybatis-migrations-3.2.0/mybatis-migrations-3.2.0.zip

[Windows]
Step 2: Create a folder C:\Program Files\mybatis\, unzip the downloaded file there. After installation, your bin folder is like: C:\Program Files\mybatis\mybatis-migrations-3.2.0\bin
Step 3: Add the MIGRATIONS_HOME environment variable in the user variables with the value C:\Program Files\mybatis\mybatis-migrations-3.2.0 . Be sure to omit any quotation marks around the path even if it contains spaces; Add the MIGRATIONS environment variable in the user variables with the value %MIGRATIONS_HOME%\bin ; Update/create the Path environment variable in the user variables and prepend the value %MIGRATIONS% to add MyBatis Migrations available in the command line.
Step 4: Restart your Shell
Step 5: In the command line, go to the migrations folder of your project, eg. commons/src/main/database/migrations

[Ubuntu]
Step 2: Create a folder /usr/local/mybatis/, unzip the downloaded file there. After installation, your bin folder is like: /usr/local/mybatis/mybatis-migrations-3.2.0/bin
Step 3: Add the following lines to ~/.profile
export MIGRATIONS_HOME=/usr/local/mybatis/mybatis-migrations-3.2.0
export MIGRATIONS=$MIGRATIONS_HOME/bin
export PATH=$MIGRATIONS:$PATH
Step 4: Run .profile: source ~/.profile
Step 5: Copy migrations folder from your project to  ~/3qnow/migrations/. Change directory to ~/3qnow/migrations/.

Step 6:  If you don't have any existing database running, run "migrate bootstrap"; You should run this only once.
Step 7: run "migrate up" to apply the latest changes. In the future, you could always run this command to get the latest database updates if you know if there is any. "migrate status" would help find out the truth.
Step 8: In the case that you want to roll back to the previous database status, run "migrate down" for one step back or "migrate down n" for n steps back. You could always run "migrate status" to check the status. 


Reference: http://mybatis.github.io/migrations/index.html
