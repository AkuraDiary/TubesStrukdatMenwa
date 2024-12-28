package views.operator;
import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.MASTER_CUSTOMER;
import static views.AppRouter.AppRoute.OPERATOR_MENU;

import presenter.CustomerPresenter;
import util.*;
import views.AppRouter;

public class MasterCustomerMenu {

    private final CustomerPresenter custPresenter;
  
    public MasterCustomerMenu(CustomerPresenter custPresenter){
        this.custPresenter=custPresenter;
    }

    public void showMasterCustomerMenu(){
        InputUtilities.cls();
        while (AppRouter.activeRoute== MASTER_CUSTOMER) {
            try {
                System.out.println("Master Customer Menu");
                System.out.println("1. List Customer");
                System.out.println("2. Tambah Customer");
                System.out.println("3. Edit Customer");
                System.out.println("4. Hapus Customer");
                System.out.println("0. Kembali");
                System.out.println();
                System.out.print("Masukkan Pilihan :");
                String inputUser = InputUtilities.inputReader.readLine();
                switch (inputUser) {
                    case "1":
                        tampilanListCustomer();
                        break;
                
                    case "2":
                        tambahCustomer();
                        break;
                
                    case "3":
                        editCustomer();
                        break;
                
                    case "4":
                        hapusCustomer();
                        break;
                
                    case "0":
                        AppRouter.navigateTo(OPERATOR_MENU);
                        break;
                
                    default:
                        invalidChoice();;
                }
            } catch (Exception e) {
                // TODO: handle exception
                invalidChoice();
            }            
        }
    }

    private void hapusCustomer(){
        try {
            System.out.println("Pilih Customer untuk dihapus");
            System.out.print("Masukkan ID Customer untuk dihapus : ");
            int custID = InputUtilities.readInt();
            custPresenter.selectCustomer(custID);
            System.out.print("[Konfirmasi] apakah anda yakin untuk menghapus data ini? [y/n]: ");
            String jawab = InputUtilities.readLine();
            if (jawab != null && jawab.equalsIgnoreCase("y")) {
                custPresenter.removeCustomer(custID);
                System.out.println();
            }
        } catch (Exception e) {
            // TODO: handle exception
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
        }
    }

    private void editCustomer(){
        try {
            System.out.println("Pilih Customer untuk di edit");
            System.out.println("Masukkan ID Customer untuk dipilih : ");
            int custId = InputUtilities.readInt();

            custPresenter.selectCustomer(custId);

            if (custPresenter.selectedCust != null) {
                System.out.println("Edit Data Customer");
                System.out.println("Tambah Customer");
                System.out.print("Masukkan Nama Customer : ");
                String custName = InputUtilities.readLine();
                System.out.print("Masukkan Email Custoemer : ");
                String custEmail = InputUtilities.readLine();
                System.out.print("Masukkan Nomor identitas : ");
                String custNumIdent = InputUtilities.readLine();
                System.out.print("Masukkan Nomor Telpon : ");
                String custTelp = InputUtilities.readLine();
                System.out.print("Masukkan Alamat : ");
                String custAddres = InputUtilities.readLine();

                custPresenter.updateCustomer(custId, custName, custEmail, custNumIdent, custTelp, custAddres);
            System.out.println("Data Cust berhasil di edit ");
            System.out.println();
            
            } 
        } catch (Exception e) {
            // TODO: handle exception
        Formatter.formatMessageOutput(e.getMessage()); 
        invalidChoice();       
        }
    }

     private void   tambahCustomer(){
        try {
            InputUtilities.cls();
            System.out.println("Tambah Customer");
            System.out.print("Masukkan Nama Customer : ");
            String custName = InputUtilities.readLine();
            System.out.print("Masukkan Email Custoemer : ");
            String custEmail = InputUtilities.readLine();
            System.out.print("Masukkan Nomor identitas : ");
            String custNumIdent = InputUtilities.readLine();
            System.out.print("Masukkan Nomor Telpon : ");
            String custTelp = InputUtilities.readLine();
            System.out.print("Masukkan Alamat : ");
            String custAddres = InputUtilities.readLine();

            custPresenter.saveCustomer(
                -1,
                custName,
                custEmail,
                custNumIdent,
                custTelp,
                custAddres
            );
            System.out.println("Customer Berhasil Ditambahkan");
            System.out.println();

        } catch (Exception e) {
            // TODO: handle exception
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
        }
     }

    private void tampilanListCustomer(){
        System.out.println();
        System.out.println("List Customer");
        custPresenter.getAllCustomers().display();
        System.out.println();
    }
}

