package service;

import model.Drink;
import repository.DrinkRepository;
import repository.DrinkUpdateRepository;

import java.io.IOException;
import java.util.List;

public class DrinkService {
    private DrinkRepository drinkRepository;
    private DrinkUpdateRepository drinkUpdateRepository;

    public DrinkService() {
        drinkRepository = new DrinkRepository();
        drinkUpdateRepository = new DrinkUpdateRepository();
    }

    public List<Drink> getAllDrink() throws IOException {
        return drinkRepository.getAll();
    }
    public Drink getDrinkById(long id) throws IOException {
        return drinkRepository.getById(id);
    }

    public List<Drink> getAllDrinkUpdate() throws IOException {
        return drinkUpdateRepository.getAll();
    }

    public long checkIdDrink(long id) throws IOException {
        return drinkRepository.checkID(id);
    }

    public int checkNameDrink(String name) throws IOException {
        return drinkRepository.checkName(name);
    }

    public void deleteDrinkById(long id) throws IOException {
        drinkRepository.deleteById(id);
    }

    public void deleteDrinkUpdateById(long id) throws IOException {
        drinkUpdateRepository.deleteById(id);
    }
}
