package Services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import MainMenu.Terminal;

public class ServiceAdd extends Service {
    private Terminal con = new Terminal();

    public void addService() throws IOException {
        Service service = new Service();

        System.out.println("================================");
        System.out.println("Service Code: ");
        service.setServiceCode(con.sc.nextLine());

        System.out.println("Description: ");
        service.setDescription(con.sc.nextLine());

        System.out.println("Price ");
        service.setPrice(con.sc.nextLine());

        int checkSave = 1;
        do {
            System.out.println("Add new Service? [Y/N]");
            String saveService = con.sc.nextLine();
            if (saveService.equals("N")) {
                System.out.print("Service not saved!");
                checkSave = 0;

            } else if (saveService.equals("Y")) {
                FileWriter fw = new FileWriter("Database\\Services.txt", true);
                FileWriter fw2 = new FileWriter("Database\\Lab\\" + service.getServiceCode() + "_Requests.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(service.getServiceCode());
                bw.write(";");
                bw.write(service.getDescription());
                bw.write(";");
                bw.write(service.getPrice());
                bw.write(";");

                bw.newLine();
                System.out.println("Service saved!");
                bw.close();
                fw.close();
                fw2.close();
                checkSave = 0;

            } else {
                System.out.println("Incorrect input, please try again");
                checkSave = 1;
            }
        } while (checkSave == 1);

        System.out.println("Do you want to add another service? [Y/N]");
        String again = "Y";
        again = con.sc.nextLine();
        if (again.equals("Y")) {
            addService();
        }

    }

}
