## Steps to run this application on OpenShift ##

1. Sign up for an OpenShift account at https://openshift.redhat.com/app/account/new

2. Install client tools. After you have installed ruby 1.8.7 or above and git, do the following
```
sudo gem install rhc
```

3. Setup your OpenShift account. This will create a domain name,create ssh keys, and upload them to OpenShift server
```
rhc setup
```

4. Create a tomcat7 application with MongoDB database
```
rhc app create bookshop tomcat-7 mongodb-2.2
```

5. Add a git remote and pull code from github repository
```
git rm -rf src/ pom.xml
git commit -am "removed default files"
git remote add upstream -m master git://github.com/shekhargulati/bookshop-bootcamp-rest-quickstart.git
git pull -s recursive -X theirs upstream master
```
6. Push the code
```
git push
```

7. After code is pushed to OpenShift, view the running app at http://bookshop-domainname.rhcloud.com. Replace domainname with your own domain name.