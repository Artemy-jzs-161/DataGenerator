package org.cbr.oldfiles;


import java.util.TreeSet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Генерация перс данных
     //  PersonData personData = new PersonData();
     //  System.out.println(personData);

     //  //Генерация снилса
     //  String snils = SNILSGenerator.generateSnils();
     //  System.out.println(snils + " СНИЛС");

     //  //Генерация ИНН физлица 12 цифр
     //  String innF = INNGenerator.generateIndividualInn();
     //  System.out.println(innF + " ИНН физ.лица");

     //  //Генерация ИНН юрлица 10 цифр
     //  String innUl = INNGenerator.generateEntryInn();
     //  System.out.println(innUl + " ИНН юр.лица");

     //  //Генерация КПП
     //  String kpp = KPPGenerator.generate();
     //  System.out.println(kpp + " КПП");
     //  System.out.println(KPPGenerator.getDescription(kpp));

     //  // Генерация полного комплекта документов
     //  RussianRegion region = RussianRegion.MOSCOW;
     //  RegistrationReason reason = RegistrationReason.REASON_01;

     //  String ogrn = OGRNGenerator.generate(OgrnType.LEGAL_ENTITY, reason, region);

     //  System.out.println("Комплект документов для " + region.getName());
     //  System.out.println("ОГРН: " + ogrn);
     //  System.out.println("КПП: " + kpp);
     //  System.out.println("Причина: " + reason.getDescription());


        TreeSet<Integer> treeSet = new TreeSet<>();

        treeSet.add(1);  // Добавляем 1
        treeSet.add(1);  // Пытаемся добавить ещё раз (не добавится)
        treeSet.add(1);  // Снова не добавится

        System.out.println(treeSet);  // Вывод: [1]
}

    }
