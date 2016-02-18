package fluxoconsultoria.ufrj.br.surgerynote.model.dao;

/**
 * Created by ericreis on 6/15/15.
 */
public interface DaoGenerics<T>
{
    public long save(T t);
    public boolean update(T t);
    public boolean delete(T t);

}
