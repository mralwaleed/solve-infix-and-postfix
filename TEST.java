
public class TEST {
	public static <T> void main(String[] args) {
		int j=0;
		{
			//0
			String exp = "( 3 + 5";
			System.out.println(j++);
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at:: " + ife.validate());
		}
		{
			//1
			String exp = "5 + 3 +";
			System.out.println(j++);
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			//2
			String exp = "( 5 + ) + 5";
			System.out.println(j++);

			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			//3
			String exp = "( 5 + 5 ) * ( + 5 )";
			System.out.println(j++);

			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			//4
			String exp = "4 + 3 ( 5 + 4 )";
			System.out.println(j++);
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			//5
			String exp = "( 5 + 4 ) 5 + 6";
			System.out.println(j++);
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			//6
			String exp = "4 + 3 )";
			System.out.println(j++);
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			//7
			String exp = ") 4 + 3";
			System.out.println(j++);
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			//8
			String exp = "( 4 + 3";
			System.out.println(j++);
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			//9
			String exp = "4 + 3 + ( 5 + 4";
			System.out.println(j++);
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			//10
			String exp = "4 + 3 + 5 + 4 + (";
			System.out.println(j++);
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			//11
			String exp = "4 + 3 5 + 4";
			System.out.println(j++);
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			//12
			String exp = "4 + 3";
			System.out.println(j++);
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			//13
			String exp = "( )";
			System.out.println(j++);
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			//14
			String exp = "";
			System.out.println(j++);
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
	
	}
}
