class rpgstack {
        scorearray[] st; 
        int top=-1;
       void arraydec(int tgn){
         st=new scorearray[tgn];
       }   
       void push( scorearray g){
        if (st == null) {
            System.out.println("STACK NOT INITIALIZED");
            return;
        }
        if (top >= st.length - 1) {
            System.out.println("STACK OVERFLOW");
            return;
        }
        st[++top]=g;
       }
        scorearray peek(){
       if(st == null || top==-1){
       System.out.println("THE STACK IS EMPTY OR NOT INITIALIZED");
        return null;
       }
        return st[top];
       }
       
       scorearray pop(){
        if(st == null || top==-1){
            System.out.println("NOTHING TO UNDO OR STACK NOT INITIALIZED");
            return null;
        }
        return st[top--];
       }
    }
