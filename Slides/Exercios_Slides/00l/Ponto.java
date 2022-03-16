class Ponto{
	//estou no vim por enquanto, entao perdao se a identacao estiver ruim. 
	private double x; 
	private double y; 
	private int id;
	private int nextID;//o valor padrao de uma int inicializada no java sempre eh 0.
	//no caso dos getters, eu tenho quase certeza q o this nao eh necessario, mas eh bom colocar pra ficar mais explicito.
	public Ponto(){};

	public Ponto(double x, double y){
		this.x = x;
		this.y = y;
	}

	public Ponto(double x, double y, int id){
		this.x = x;
		this.y = y;
		this.id = id;
		this.nextID = id + 1;
	}
	public static double getX(){
		return this.x;
	}

	public static double getY(){
		return this.y;
	}

	public static void setX(double x){
		this.x = x;
	}
	
	public static void setY(double y){
		this.y = y;
	}
}
