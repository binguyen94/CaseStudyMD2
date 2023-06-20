package repository;

import service.FileService;

import java.io.IOException;
import java.util.List;

public class FileContext<T> {
    protected Class<T> tClass;
    protected String filePath;
    private FileService fileService;

    public FileContext() {
        fileService = new FileService();
    }

    public List<T> getAll() throws IOException {
        return fileService.readData(filePath, tClass);
    }
    public T getById(long id) throws IOException {
        List<T> list = getAll();
        for (T t : list) {
            IModel<T> iModel = (IModel<T>) t;
            if (iModel.getID() == id) {
                return t;
            }
        }
        return null;
    }
    public long checkID(long id) throws IOException {
        List<T> list = getAll();
        for (int i = 0; i < list.size(); i++) {
            IModel<T> iModel = (IModel<T>) list.get(i);
            if (iModel.getID() == id){
                return 1;
            }
        }
        return -1;
    }

    public int checkName(String name) throws IOException {
        List<T> list = getAll();
        for (int i = 0; i < list.size(); i++) {
            IModel<T> iModel = (IModel<T>) list.get(i);
            if (iModel.getName().equals(name)) {
                return 1;
            }
        }
        return -1;
    }

    public void deleteById(long id) throws IOException {
        List<T> list = getAll();
        for (int i = 0; i < list.size(); i++) {
            IModel<T> iModel = (IModel<T>) list.get(i);
            if (iModel.getID() == id) {
                list.remove(i);
                break;
            }
        }
        fileService.writeData(filePath, list);
    }

    public void add(T newObj) throws IOException {
        List<T> list = getAll();
        list.add(newObj);
        fileService.writeData(filePath, list);
    }
}
