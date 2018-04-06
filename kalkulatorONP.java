package kalkulatorONP;

public class kalkulatorONP {
	
	
	public static boolean  priority_check(String stos, String args) {
		int p1 = 0;
		int p2 = 0;
		
		switch(stos) {
		case "+":
			p1=1;
			break;
		case "-":
			p1=1;
			break;
		case "x":
			p1=2;
			break;
		case "/":
			p1=2;
			break;
		default:
			break;
		}
		
		switch(args) {
		case "+":
			p2=1;
			break;
		case "-":
			p2=1;
			break;
		case "x":
			p2=2;
			break;
		case "/":
			p2=2;
			break;
		default:
			break;
		}
		return (p1>=p2);
	}

	public static void main(String[] args)
	{	
		int dlugosc=0;
		while(args[0].charAt(dlugosc) != '=') 
			dlugosc++;
		
		System.out.println("Wyrazenie:");
		for(int i=0;i<dlugosc+1;i++) {
			System.out.print(args[0].charAt(i));
		}

		String[] stos = new String[dlugosc];
		String[] wyjscie = new String[dlugosc];
		double[] wejscie = new double[dlugosc];
		
		int tmp_wy = 0;
		int tmp_st = 0;
		
		//sprawdzamy wyrazenie
		for(int i=0;i<dlugosc;) {
			
		if(Character.isDigit(args[0].charAt(i))) {
			wyjscie[tmp_wy]=Character.toString(args[0].charAt(i));
			i++;
			while(i<dlugosc&&(Character.isDigit(args[0].charAt(i)) || args[0].charAt(i) == '.')) {
				wyjscie[tmp_wy]+=args[0].charAt(i);
				i++;
			}
			tmp_wy++;
		}
		else {
			if(args[0].charAt(i)=='('){
				stos[tmp_st]=Character.toString(args[0].charAt(i));
				tmp_st++;
			}
			else if(args[0].charAt(i)==')') {
				while(!stos[tmp_st-1].equals("(")) {
					wyjscie[tmp_wy]=stos[tmp_st-1];
					tmp_wy++;
					tmp_st--;
				}
				tmp_st--;
			}
			else {
				if(tmp_st>0) {
					if(!stos[tmp_st-1].equals("(") && priority_check(stos[tmp_st-1],Character.toString(args[0].charAt(i)))) {
						wyjscie[tmp_wy]=stos[tmp_st-1];
						tmp_wy++;
						stos[tmp_st-1]=Character.toString(args[0].charAt(i));
					}
					else {
						stos[tmp_st]=Character.toString(args[0].charAt(i));
						tmp_st++;
					}
				}
				else {
					stos[tmp_st]=Character.toString(args[0].charAt(i));
					tmp_st++;
				}
			}
			i++;
		}
	}
		for(;tmp_st!=0;tmp_st--) {
			wyjscie[tmp_wy]=stos[tmp_st-1];
			tmp_wy++;
		}
		System.out.println("");
		System.out.println("ONP:");
		
		for(int i=0;i<dlugosc-1;i++) {
			System.out.print(wyjscie[i]+" ");
		}
	//obliczanie
		int tmp_we = 0;
		int tmp = tmp_wy;
	
		for(int i=0;i<tmp;i++) {
			if(Character.isDigit(wyjscie[i].charAt(0)))
			{
				wejscie[tmp_we] = Double.parseDouble(wyjscie[i]);
				tmp_we++;
			}
			else {
				switch(wyjscie[i]) {
				case "+":
					wejscie[tmp_we-2]=wejscie[tmp_we-2]+wejscie[tmp_we-1];
					break;
				case "-":
					wejscie[tmp_we-2]=wejscie[tmp_we-2]-wejscie[tmp_we-1];
					break;
				case "x":
					wejscie[tmp_we-2]=wejscie[tmp_we-2]*wejscie[tmp_we-1];
					break;
				case "/":
					wejscie[tmp_we-2]=wejscie[tmp_we-2]/wejscie[tmp_we-1];
					break;
				default:
					break;
				}
				tmp_we--;
			}
		}
		System.out.println("");
		System.out.println("Wynik: "+wejscie[0]);
	}
}