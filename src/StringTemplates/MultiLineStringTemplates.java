package StringTemplates;

public class MultiLineStringTemplates {
    public static void main(String[] args) {

        String deploymentName = "nginx-deployment";
        Integer noOfReplicas = 3;
        Integer containerPort = 443;
        String kubectldeployfile = STR."""
    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: \{deploymentName}
    spec:
      replicas: \{noOfReplicas}
      selector:
        matchLabels:
          app: nginx
      template:
        metadata:
          labels:
            app: nginx
        spec:
          containers:
          - name: nginx-container
            image: nginx:latest
            ports:
            - containerPort: \{containerPort}
    """;
        System.out.println(kubectldeployfile);
    }
}
