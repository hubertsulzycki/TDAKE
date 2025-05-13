package lab.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import lab.dto.ComplaintDTO;

public class Main {
    public static void main(String[] args) {
        String baseUrl = "http://localhost:8080/Server-1.0-SNAPSHOT/api/complaints";
        Client client = ClientBuilder.newClient();

        System.out.println("\nWszystkie skargi:");
        ComplaintDTO[] allComplaints = client.target(baseUrl)
                .request(MediaType.APPLICATION_JSON)
                .get(ComplaintDTO[].class);
        for (ComplaintDTO c : allComplaints) {
            System.out.println(c.getId() + ": " + c.getComplaintText() + " | Status: " + c.getStatus());
        }

        System.out.println("\nOtwarta skarga:");
        ComplaintDTO complaintToUpdate = client.target(baseUrl + "/254")
                .request(MediaType.APPLICATION_JSON)
                .get(ComplaintDTO.class);
        System.out.println(complaintToUpdate.getComplaintText() + " | Status: " + complaintToUpdate.getStatus());

        complaintToUpdate.setStatus("closed");
        client.target(baseUrl + "/" + 254)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(complaintToUpdate));
        System.out.println("\nZmieniono status skargi na 'closed'.");

        System.out.println("\nOtwarte skargi po aktualizacji:");
        ComplaintDTO[] openComplaintsAfterUpdate = client.target(baseUrl)
                .queryParam("status", "open")
                .request(MediaType.APPLICATION_JSON)
                .get(ComplaintDTO[].class);
        for (ComplaintDTO c : openComplaintsAfterUpdate) {
            System.out.println(c.getId() + ": " + c.getComplaintText() + " | Status: " + c.getStatus());
        }

        client.close();

    }
}
