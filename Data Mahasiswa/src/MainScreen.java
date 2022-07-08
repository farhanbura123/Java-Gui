import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private JPanel panelMain;
    private JList jListDataMahasiswa;
    private JButton filterButton;
    private JTextField textFieldFilter;
    private JTextField textFieldNim;
    private JTextField textFieldNama;
    private JTextField textFieldIpk;
    private JButton buttonSave;
    private JButton clearButton;
    private JButton buttonDelete;

    List<Mahasiswa> arrayData = new ArrayList<>();

    private DefaultListModel defaultListModel = new DefaultListModel();

    class Mahasiswa{
        private String nama;

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public float getIpk() {
            return ipk;
        }

        public void setIpk(float ipk) {
            this.ipk = ipk;
        }

        private String nim;
        private float ipk;
    }

    public MainScreen(){
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(false);
        this.pack();
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldNama.getText();
                String nim = textFieldNim.getText();
                Float ipk = Float.valueOf(textFieldNim.getText());

                Mahasiswa mahasiswa = new Mahasiswa();

                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);
                mahasiswa.setIpk(ipk);

                arrayData.add(mahasiswa);
                fromMahasiswaToListModel();
            }

        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setBtnClear();

            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListDataMahasiswa.getSelectedIndex();

                if(index < 0)
                    return;

                String nama = jListDataMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arrayData.size(); i++) {
                    if (arrayData.get(i).getNama().equals(nama)){
                        arrayData.remove(i);
                        break;
                    }

                }

                setBtnClear();
                fromMahasiswaToListModel();
            }
        });
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyWord = textFieldFilter.getText();

                List<String> filtered = new ArrayList<>();

                for (int i = 0; i < arrayData.size(); i++) {
                    if (arrayData.get(i).getNama().contains(keyWord)){
                        filtered.add(arrayData.get(i).getNama());
                    }

                }
                refreshListModel(filtered);
            }
        });
    }

    private void fromMahasiswaToListModel(){
        List<String> listNamaMahasiswa = new ArrayList<>();

        for (int i = 0; i < arrayData.size(); i++) {
            listNamaMahasiswa.add(
                    arrayData.get(i).getNama()
            );
            refreshListModel(listNamaMahasiswa);
        }

    }

    private  void setBtnClear(){
        textFieldIpk.setText("");
        textFieldNama.setText("");
        textFieldNim.setText("");

    }

    private void refreshListModel(List<String> list){
        defaultListModel.clear();
        defaultListModel.addAll(list);
        jListDataMahasiswa.setModel(defaultListModel);
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }
}
