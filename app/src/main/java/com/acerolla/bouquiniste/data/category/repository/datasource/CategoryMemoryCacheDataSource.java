package com.acerolla.bouquiniste.data.category.repository.datasource;

import com.acerolla.bouquiniste.data.category.entity.CategoryChildrenData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
class CategoryMemoryCacheDataSource implements ICategoryDataSource {

    private List<CategoryParentData> mCategories;


    @Override
    public List<CategoryParentData> getCategories() {
        if (mCategories == null) {
            initCategories();
        }

        return mCategories;
    }

    private void initCategories() {
        mCategories = new ArrayList<>();

        ArrayList<CategoryChildrenData> children = new ArrayList<>();
        children.add(new CategoryChildrenData("Современная литература"));
        children.add(new CategoryChildrenData("Советские писатели"));
        children.add(new CategoryChildrenData("Классики литературы"));
        children.add(new CategoryChildrenData("Исторический роман"));
        children.add(new CategoryChildrenData("Мифы, сказки, фольклор"));
        children.add(new CategoryChildrenData("Литература античная и средних веков"));
        children.add(new CategoryChildrenData("Ужасы, мистика"));
        children.add(new CategoryChildrenData("Приключения, путешествия"));
        children.add(new CategoryChildrenData("Мемуары, биографии"));
        children.add(new CategoryChildrenData("Сатира, юмор"));
        mCategories.add(new CategoryParentData(
                "Художественная литература",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Аппаратное обеспечение (железо)"));
        children.add(new CategoryChildrenData("Офисные приложения"));
        children.add(new CategoryChildrenData("Базы данных"));
        children.add(new CategoryChildrenData("Графика, дизайн, верстка"));
        children.add(new CategoryChildrenData("Игры и развлечения"));
        children.add(new CategoryChildrenData("Сети, телекоммуникации, Интернет"));
        mCategories.add(new CategoryParentData(
                "Компьютеры",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Бухгалтерия, аудит"));
        children.add(new CategoryChildrenData("Юридическая литература"));
        children.add(new CategoryChildrenData("Реклама, маркетинг, продажи"));
        children.add(new CategoryChildrenData("Документооборот"));
        children.add(new CategoryChildrenData("Страхование"));
        children.add(new CategoryChildrenData("Налогообложение"));
        mCategories.add(new CategoryParentData(
                "Деловая литература",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Биология, генетика"));
        children.add(new CategoryChildrenData("Физика, астрономия"));
        children.add(new CategoryChildrenData("Химия"));
        children.add(new CategoryChildrenData("Языкознание, иностранные языки"));
        children.add(new CategoryChildrenData(" Математика, статистика"));
        children.add(new CategoryChildrenData("Технологии и инженерия"));
        children.add(new CategoryChildrenData("Педагогика"));
        children.add(new CategoryChildrenData("Литературоведение"));
        children.add(new CategoryChildrenData("Психология"));
        mCategories.add(new CategoryParentData(
                "Наука, образование",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Математические науки"));
        children.add(new CategoryChildrenData(" Иностранные языки"));
        children.add(new CategoryChildrenData("Физика, астрономия"));
        children.add(new CategoryChildrenData("Биология, природоведение"));
        children.add(new CategoryChildrenData("История"));
        children.add(new CategoryChildrenData("Информатика"));
        children.add(new CategoryChildrenData("География"));
        children.add(new CategoryChildrenData("Решения и ответы"));
        children.add(new CategoryChildrenData("Пособия для учителей"));
        mCategories.add(new CategoryParentData(
                "Школьные учебники",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Познавательная"));
        children.add(new CategoryChildrenData("Художественная"));
        children.add(new CategoryChildrenData("Для самых маленьких"));
        mCategories.add(new CategoryParentData(
                "Детская литература",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Садоводство, огородничество"));
        children.add(new CategoryChildrenData("Домашние животные"));
        children.add(new CategoryChildrenData("Кулинария"));
        children.add(new CategoryChildrenData("Домоводство"));
        children.add(new CategoryChildrenData("Оформление, дизайн"));
        children.add(new CategoryChildrenData("Рукоделие"));
        mCategories.add(new CategoryParentData(
                "Семья, дом, дача",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Автомобили, наземный транспорт"));
        children.add(new CategoryChildrenData("Атласы дорог и рек"));
        children.add(new CategoryChildrenData("Корабли, судостроение"));
        children.add(new CategoryChildrenData("Технология и оборудование производств"));
        children.add(new CategoryChildrenData("Строительство"));
        children.add(new CategoryChildrenData("Оружие"));
        mCategories.add(new CategoryParentData(
                "Техника и технология",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Спорт, здоровый образ жизни"));
        children.add(new CategoryChildrenData("Медицина для специалистов"));
        children.add(new CategoryChildrenData("Народная и нетрадиционная медицина"));
        children.add(new CategoryChildrenData("Самосовершенствование"));
        children.add(new CategoryChildrenData("Сексуальное здоровье, эротика"));
        children.add(new CategoryChildrenData("Охота, рыболовство"));
        mCategories.add(new CategoryParentData(
                "Медицина, спорт, здоровье",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Марксизм, ленинизм, сталинизм"));
        children.add(new CategoryChildrenData("Военная книга, разведка, внутренняя безопасность"));
        children.add(new CategoryChildrenData("Философия"));
        children.add(new CategoryChildrenData("История"));
        children.add(new CategoryChildrenData("Религия"));
        children.add(new CategoryChildrenData("Иные социальные явления"));
        mCategories.add(new CategoryParentData(
                "Общество, политика, религия",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Справочники"));
        children.add(new CategoryChildrenData("Энциклопедии"));
        children.add(new CategoryChildrenData("Словари"));
        children.add(new CategoryChildrenData("Путеводители"));
        mCategories.add(new CategoryParentData(
                "Специальная и справочная литература",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Альбомы, подарочные издания"));
        children.add(new CategoryChildrenData("Музыка"));
        children.add(new CategoryChildrenData("Живопись, графика, рисунок, скульптура"));
        children.add(new CategoryChildrenData("Фотография"));
        children.add(new CategoryChildrenData("Театр, кино, ТВ"));
        children.add(new CategoryChildrenData("Балет, танец, пластика"));
        mCategories.add(new CategoryParentData(
                "Искусство, культура",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Филателия"));
        children.add(new CategoryChildrenData("Нумизматика"));
        children.add(new CategoryChildrenData("Книги"));
        children.add(new CategoryChildrenData("Антиквариат"));
        children.add(new CategoryChildrenData("Настольные игры"));
        children.add(new CategoryChildrenData("Фалеристика"));
        mCategories.add(new CategoryParentData(
                "Хобби, коллекционирование",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Программирование"));
        children.add(new CategoryChildrenData("Программирование"));
        children.add(new CategoryChildrenData("Программирование"));
        children.add(new CategoryChildrenData("Программирование"));
        children.add(new CategoryChildrenData("Программирование"));
        children.add(new CategoryChildrenData("Программирование"));
        mCategories.add(new CategoryParentData(
                "Компьютеры",
                children
        ));

        children = new ArrayList<>();
        children.add(new CategoryChildrenData("Астрология"));
        children.add(new CategoryChildrenData("Алхимия"));
        children.add(new CategoryChildrenData("Оккультизм, магия, колдовство"));
        children.add(new CategoryChildrenData("Парапсихология"));
        children.add(new CategoryChildrenData("НЛО, пришельцы"));
        children.add(new CategoryChildrenData("Непознанное"));
        mCategories.add(new CategoryParentData(
                "Мистика, эзотерика, непознанное",
                children
        ));
    }

    @Override
    public void release() {
        mCategories = null;
    }
}
