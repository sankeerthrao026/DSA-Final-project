class highscorell {
      LinkedList newnode=null,head=null,cnode=null;
      void insert( score high){
       newnode= new LinkedList();
       newnode.data=high;
        if (head==null) {
              head=newnode;
        }
        else{
            cnode=head;
            while (cnode.next!=null) {
             cnode=cnode.next;
            }
            cnode.next=newnode;
        }
        newnode.next=null;
        System.out.println("LIST CREATED SUCCESFULLY");
    }
     void displayhighll(int w){
     if(head==null){
    System.out.println("INVALID LIST");
     }
     else{
      cnode=head;
       while(cnode!=null){
        System.out.println("HIGH SCORE RESULTS IN GAME "+w+" is...");
        System.out.print("PLAYER ID: "+cnode.data.playerid+ "PLAYER SCORE: "+cnode.data.sco+ "-->");
        cnode=cnode.next;
    } 
    }
    }
    }
