package script;


def helloWithParam(userName){
    println "start to call helloWithParam, param{userName:" + userName + "}";
    return "success, helloWithParam";
}

helloWithParam(userName);