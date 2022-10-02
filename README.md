# service-template

## Entidade
Crie uma entenidade com os atributos que precisar e herde de EntityBase passando como argumento qual o será o tipo do ID. No nosso caso, Integer 

```
@Entity
@Table(name "employee")
public class EmployeeEntity extends EntityBase<Integer> {
    
   @NonNUll
   @Column(name = "name")
   private String name;

   @CPF
   @Column(name = "CPF")
   private String cpf;
 
}
```
## Repository
Crie um repository padrão do Spring Data JPA passando a entidade criada 
``` 
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {}
```
## DTO
Crie um DTO com os mesmo campos (ou campos que quer expor) da entidade
```
@Data
public class EmployeeDto {
    
   @NonNUll
   private String name;

   @CPF(message = "Documento Inválido") 
   private String cpf;
 
}
```

## Service
Crie um Service que herde de CrudService, passando como parametros a Entidade, Dto e Repositório criados. 
A classe CrudService é abstrata e nos obriga a implementar 3 métodos que fazem o mapeamento de Entidade para DTO e vice e versa. 
Para a implementação usaremos o MapStruct (já incluso no projeto)
```
public class EmployeeService extends CrudService<EmployeeDTO, EmployeeEntity, EmployeeRepository> {

    @Autowired
    private EmployeeMapper mapper;


    @Override
    protected EmployeeEntity toEntity(final EmployeeDTO model) {
        return mapper.toEntity(model);
    }

    @Override
    protected EmployeeDTO toModel(final EmployeeEntity entity) {
        return mapper.toModel(entity);
    }

    @Override
    protected List<EmployeeDTO> toModel(final List<EmployeeEntity> entity) {
        return mapper.toModel(entity);
    }
}
```

## Controller
Por fim vamos criar o controller herdando da classe Controller. Passando como argumento o Service e o DTO que criamos anteriormente
```
@RestController
@RequestMapping("/employee")
public class EmployeeController extends Controller<EmployeeService, EmployeeDTO> {}
```
