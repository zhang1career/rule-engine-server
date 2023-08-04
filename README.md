# deploy

```shell
scp -i ~/.ssh/ec2-user_aws_zhang_workstation.pem -r /Users/zhangrj/Projects/rule-engine/rule-engine-rest/target/rule-engine-rest-1.0.0-SNAPSHOT.zip ec2-user@54.86.102.80:/project/rule-engine/
```


# run

```shell
cd /project/rule-engine
./run.sh
```

# function
```bash
curl 'http://54.86.102.80:10001/rule/eval' \
-d 'rule="{\"name\":\"大于\",\"type\":\"OP_GT\",\"value\":[{\"name\":\"年龄\",\"type\":\"VAR_INT\",\"value\":\"age\"},{\"name\":\"18\",\"type\":\"INS_INT\",\"value\":\"18\"}]}"' \
-d 'age="17"'
```