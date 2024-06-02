package tech.noetzold.JVMParamsCompare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int number;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ElementCollection
    private List<String> list;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String longText;
    @Lob
    private byte[] file;

}
