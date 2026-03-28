class scorequeue {
    score[] q;
    int front=0,rear=-1,size;

    void create(int n){
        size=n;
        q=new score[n];
    }

    void enqueue(score s){
        if(q == null){
            System.out.println("QUEUE NOT INITIALIZED");
            return;
        }
        if(rear==size-1){
            System.out.println("QUEUE FULL");
            return;
        }
        q[++rear]=s;
    }

    score dequeue(){
        if(q == null || front>rear){
            System.out.println("QUEUE EMPTY OR NOT INITIALIZED");
            return null;
        }
        return q[front++];
    }

    void display(){
        if(q == null || front>rear){
            System.out.println("QUEUE EMPTY OR NOT INITIALIZED");
            return;
        }
        for(int i=front;i<=rear;i++){
            if (q[i] != null) {
                System.out.println("PLAYER ID: "+q[i].playerid+" SCORE: "+q[i].sco);
            }
        }
    }
}
