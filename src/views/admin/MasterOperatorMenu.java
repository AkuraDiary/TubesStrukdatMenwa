package views.admin;

import presenter.UserPresenter;
import util.AppEnums;
import util.Encryption;
import util.Formatter;
import util.InputUtilities;
import views.AppRouter;

import static util.Formatter.invalidChoice;
import static views.AppRouter.AppRoute.*;

public class   MasterOperatorMenu  {

    private final UserPresenter userPresenter;

    public MasterOperatorMenu(UserPresenter userPresenter) {
        this.userPresenter = userPresenter;
    }

    String inputUser;

    public void showMasterOperatorMenu() {
        InputUtilities.cls();
        while (AppRouter.activeRoute == MASTER_OPERATOR) {
            try {
                System.out.println("Master Operator Menu");
                System.out.println("1. List Operator");
                System.out.println("2. Tambah Operator");
                System.out.println("3. Edit Operator");
                System.out.println("4. Hapus Operator");
                System.out.println("0. Kembali");
                System.out.println();
                System.out.print("Masukkan Pilihan : ");
                inputUser = InputUtilities.inputReader.readLine();
                switch (inputUser) {
                    case "1":
                        tampilListOperator();
                        break;
                    case "2":
                        tambahOperator();
                        break;
                    case "3":
                        editOperator();
                        break;
                    case "4":
                        hapusOperator();
                        break;
                    case "0":
                        AppRouter.navigateTo(ADMIN_MENU);
                        break;
                    default:
                        invalidChoice();
                }
            } catch (Exception e) {
                invalidChoice();
            }

        }
    }

    private void hapusOperator() {
        try{
            System.out.println("Pilih Operator untuk dihapus");
            System.out.print("Masukkan Id Operator untuk dipilih : ");
            int idOperator = InputUtilities.readInt();
            userPresenter.selectUser(idOperator);

            System.out.print("[Konfirmasi] apakah anda yakin untuk menghapus data ini? [y/n]: ");
            String jawab = InputUtilities.readLine();
            if(jawab != null && jawab.equalsIgnoreCase("y")){
                userPresenter.deleteUser(idOperator);
                System.out.println();
            }
        }catch (Exception e) {
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
        }

    }

    private void editOperator() {
        try{
            System.out.println("Pilih Operator untuk diedit");
            System.out.print("Masukkan Id Operator untuk dipilih : ");
            int idOperator = InputUtilities.readInt();

            userPresenter.selectUser(idOperator);

            if(userPresenter.selectedUser != null){
                System.out.println("Edit Data Operator (kosongkan jika tidak ingin merubah data)");
                System.out.print("Masukkan Username Baru Operator : ");
                String username = InputUtilities.readLine();
                System.out.print("Masukkan Password Baru Operator : ");
                String password = InputUtilities.readLine();
                System.out.print("Masukkan Email Baru Operator  : ");
                String email = InputUtilities.readLine();

                userPresenter.updateUser(
                        userPresenter.selectedUser.getId(),
                        username,
                        password,
                        email
                );

                System.out.println("Operator Berhasil Diupdate");
                System.out.println();
            }



        }catch (Exception e) {
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
        }
    }

    private void tambahOperator() {
        try{
            InputUtilities.cls();
            System.out.println("Tambah Operator");
            System.out.print("Masukkan Username Operator : ");
            String username = InputUtilities.readLine();
            System.out.print("Masukkan Password Operator : ");
            String password = InputUtilities.readLine();
            System.out.print("Masukkan Email Operator : ");
            String email = InputUtilities.readLine();

            userPresenter.saveUser(
                    -1,
                    username,
                    Encryption.hashPassword(password),
                    email,
                    AppEnums.Roles.OPERATOR
            );
            System.out.println("Operator Berhasil Ditambahkan");
            System.out.println();

        }catch (Exception e) {
            Formatter.formatMessageOutput(e.getMessage());
            invalidChoice();
        }
    }

    private void tampilListOperator() {
        System.out.println();
        System.out.println("List Operator");
        userPresenter.getAllOperator().displau();
        System.out.println();
    }
}
