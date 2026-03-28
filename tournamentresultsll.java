class tournamentresultsll {
     LinkedList newnode=null,head=null,cnode=null;
    void insert( score top){
       newnode= new LinkedList();
       newnode.data=top;
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
    }
    void displayll(int i){
     if(head==null){
    System.out.println("INVALID LIST");
     }
     else{
      cnode=head;
       while(cnode!=null){
        System.out.print("PLAYER ID: "+cnode.data.playerid+ "PLAYER SCORE: "+cnode.data.sco+" IN GAME"+i+"-->");
        System.out.println(" ");
        cnode=cnode.next;
     }
      System.out.println("LIST DISPLAY COMPLETED");
     }
    }
    }
