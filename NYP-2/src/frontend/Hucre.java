package frontend;

import javax.swing.*;

//hucre sınıfı buttondan kalıtım olan bir sınıf. buton olarak kullandık ama ek olarak
// id ve renk gibi degerler tanimladik.
public class Hucre extends JButton {
    private boolean isSelected;
    private String Color;
    private int index;
    private int id;


	public void setIndex(int index) {
		this.index = index;
	}

	public Hucre(){
        super();
        isSelected = false;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public String getColor() {
        return Color;
    }




    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
