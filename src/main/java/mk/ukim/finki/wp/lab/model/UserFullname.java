package mk.ukim.finki.wp.lab.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserFullname implements Serializable {

    private String name;

    private String surname;

}
