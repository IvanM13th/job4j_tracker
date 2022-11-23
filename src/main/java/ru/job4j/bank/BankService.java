package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу простой банковской системы
 * @author Ivan Marishin
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение данных о пользхователе и списке его счетов
     * осучествляется в коллекции типа HashMap
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает пользователя банковской системы в качестве параметра.
     * Если такого пользователя еще нет в системе,
     * то происходит добавлением нового пользователя в систему,
     * при этом для него сосздается пустой список счетов типа ArrayList.
     * @param user пользователь, который будет добавлен в систему
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод принимает пасспортные данные в качестве параметра.
     * Если пользователь с таким паспортом есть в системе, происходит его удалание.
     * @param passport паспортные данные пользователя, котрого необходимо удалить
     * @return возвращает true, если пользователь был удален.
     */
    public boolean deleteUser(String passport) {
        return users.remove(new User(passport, "")) != null;
    }

    /**
     * Метод принимает в качестве параметров пасспортные данные и данные нового счета.
     * Далее происходит поиск пользователя в системе по паспортным данным,
     * если пользователь найден (результат работы метод findByPassport не равен null),
     * получаем список счетов данного пользователя, проверяем нет ли среди них счета счета с такими реквизитами.
     * Если счета нет, добавляет счет в список счетов.
     * @param passport паспортные данные пользователя.
     * @param account новый счет
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод принмиает в качестве параметра паспортные данные пользователя.
     * Далее осуществляется поиск данного пользователя среди списка всех пользователей.
     * Если совпадение найдено, возвращаем пользователя.
     * @param passport паспортные данные.
     * @return клиент с искомыми паспортными данными.
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(s -> passport.equals(s.getPassport()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод принимает в качестве параметров паспортные данные и реквизиты счета.
     * Ищем пользователя в системе по паспортным данным с помощью метода findByPassport.
     * Если пользователь найден, получаем список его счетов.
     * Осущестляем поиск счета по реквизитам среди всех счетов пользователя.
     * @param passport паспортные данные
     * @param requisite реквизиты счета
     * @return счет клиента с искомыми реквизитами
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = getAccount(user);
            return accounts.stream()
                    .filter(account -> requisite.equals(account.getRequisite()))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * в качестве параметров метод принимает паспортные данные и реквизиты пользователей,
     * между которыми необходимо произвести трансфер денежных средств, а так же сумму перевода.
     * Получаем счета пользователей с помощью метода findByRequisite.
     * Осуществляем проверку, что такие счета есть в системе и сумма денежных средств на первом счете достаточна для перевода.
     * С помощью сеттеров устанвливаем новые значения балансов на счетах, тем самым осущзествив перевод.
     * @param srcPassport паспортные даннные пользователя, который хочет осуществит перевод.
     * @param srcRequisite реквизиты счета для списания.
     * @param destPassport паспортные данные пользователя, для которого перевод предназначается.
     * @param destRequisite реквизиты счета для пополнения.
     * @param amount сумма перевода
     * @return true, если перевод осуществлен
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account source = findByRequisite(srcPassport, srcRequisite);
        Account dest = findByRequisite(destPassport, destRequisite);
        if (source != null && dest != null && source.getBalance() >= amount) {
            source.setBalance(source.getBalance() - amount);
            dest.setBalance(dest.getBalance() + amount);
            return true;
        }
        return false;
    }

    /**
     * Метод принимает в качестве параметра пользователя
     * и возвращает список его счетов.
     * @param user пользователь банка
     * @return список счетов.
     */
    public List<Account> getAccount(User user) {
        return users.get(user);
    }

}
