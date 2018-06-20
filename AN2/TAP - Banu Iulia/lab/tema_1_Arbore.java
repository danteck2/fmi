class Nod{
	int data;
	Nod left;
	Nod right;	
	public Nod(int data){
		this.data = data;
		left = null;
		right = null;
	}
}

public class Arbore {
	
	public static  Nod root;
	public Arbore(){
		this.root = null;
	}
	public boolean inserare(int id){
		
		Node current = root;
		while(current!=null){
			if(current.data==id){
				Nod newNode = new Nod(id);
				if(root==null){
					root = newNode;
					return;
				}
				Nod current = root;
				Nod parent = null;
				while(true){
					parent = current;
					if(id<current.data){				
						current = current.left;
						if(current==null){
							parent.left = newNode;
							return;
						}
					}else{
						current = current.right;
						if(current==null){
							parent.right = newNode;
							return;
						}
					}
				}
				return true;
				
			}else if(current.data>id){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
		
	}
}
