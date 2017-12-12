package rushhour;

import java.awt.Color;

/*
auto_x_rood.png			194, 60, 60
auto_a_groenblauw.png	38, 156, 147
auto_b_geel.png			206, 178, 21
auto_c_lichtblauw.png	112, 186, 204
auto_d_roze.png			224, 178, 223
auto_e_blauw.png		66, 113, 199
auto_f_groen.png		34, 106, 51
auto_g_donkergroen.png	24, 70, 49
auto_h_grijs.png		141, 144, 149
auto_i_wit.png			229, 229, 204
auto_j_bruin.png		110, 73, 46
auto_k_zwart.png		44, 44, 44
truck_o_geel.png		244, 208, 87  (243, 236, 175)
truck_p_paars.png		179, 143, 233   (203, 179, 240)
truck_q_blauw.png   	110, 160, 233  (147, 184, 238)
truck_r_groen.png		109, 217, 126 (135, 224, 149)
auto2.png				236, 242, 244
truck5.png				244, 207, 86  (234, 234, 227)
 */

public class RushHourColor
{
	public static final Color X_RED = new Color(194, 60, 60);
	public static final Color A_GREENBLUE = new Color(38,  156, 147);
	public static final Color B_YELLOW = new Color(206, 178, 21);
	public static final Color C_LIGHTBLUE = new Color(112, 186, 204);
	public static final Color D_PINK = new Color(224, 178, 223);
	public static final Color E_BLUE = new Color(66, 113, 199);
	public static final Color F_GREEN = new Color(34, 106, 51);
	public static final Color G_DARKGREEN = new Color(24, 70, 49);
	public static final Color H_GRAY = new Color(141, 144, 149);
	public static final Color I_WHITE = new Color(229, 229, 204); 
	public static final Color J_BROWN = new Color(110, 73, 46);
	public static final Color K_BLACK = new Color(44, 44, 44);
	public static final Color O_YELLOW = new Color(244, 208, 87);
	public static final Color P_PURPLE = new Color(179, 143, 233);
	public static final Color Q_BLUE = new Color(110, 160, 233);
	public static final Color R_GREEN = new Color(109, 217, 126);

	public static final String[] colorStrEn =  {"Red", "Greenblue", "Yellow", "Lightblue", "Pink", "Blue", "Green","Darkgreen" } ; 


	public static String getColorCode(Car car)
	{
		Color color = car.getColor();
		int size = car.getSize();
		
		String colorCode = null;
		
		if (color.equals(X_RED))
			colorCode = "X";
		else if (color.equals(A_GREENBLUE))
			colorCode = "A";
		else if (color.equals(B_YELLOW))
			colorCode = "B";
		else if (color.equals(C_LIGHTBLUE))
			colorCode = "C";
		else if (color.equals(D_PINK))
			colorCode = "D";
		else if (color.equals(E_BLUE))
			colorCode = "E";
		else if (color.equals(F_GREEN))
			colorCode = "F";
		else if (color.equals(G_DARKGREEN))
			colorCode = "G";
		else if (color.equals(H_GRAY))
			colorCode = "H";
		else if (color.equals(I_WHITE))
			colorCode = "I";
		else if (color.equals(J_BROWN))
			colorCode = "J";
		else if (color.equals(K_BLACK))
			colorCode = "K";
		
		if (colorCode != null)
		{
			if (size == 2)
				return colorCode;
			else
				return null;
				
		}
		
		
		if (color.equals(O_YELLOW))
			colorCode = "O";
		else if (color.equals(P_PURPLE))
			colorCode = "P";
		else if (color.equals(Q_BLUE))
			colorCode = "Q";
		else if (color.equals(R_GREEN))
			colorCode = "R";
		
		if (size == 3)		
			return colorCode;
		else
			return null;
	}
}


