package org.example.task_backend_chefs.config;


import lombok.RequiredArgsConstructor;
import org.example.task_backend_chefs.entity.Chef;
import org.example.task_backend_chefs.entity.Dish;
import org.example.task_backend_chefs.entity.Kitchen_Kind;
import org.example.task_backend_chefs.repository.ChefRepository;
import org.example.task_backend_chefs.repository.DishRepository;
import org.example.task_backend_chefs.repository.KitchenKindRepository;
import org.example.task_backend_chefs.repository.RateAvgRepository;
import org.example.task_backend_chefs.service.ChefServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class SQLRunner implements CommandLineRunner {
    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;
    private final KitchenKindRepository kitchenKindRepository;
    private final RateAvgRepository rateAvgRepository;
    private final ChefServiceImpl chefService;



    @Override
    public void run(String... args) throws Exception {
        //1.initialize series & categories& Actors & Directors:
        if (chefRepository.count() == 0) {
            if (dishRepository.count() == 0) {
                //Western
                var spaghetti_Carbonara = dishRepository.save(Dish.builder()
                        .id(1L)
                        .name("Spaghetti Carbonara")
                        .description(" This classic Italian pasta dish combines a " +
                                "silky cheese sauce with crisp pancetta and black pepper.")
                        .price(12)
                        .build()
                );
                var pizza_Margherita = dishRepository.save(Dish.builder()
                        .id(2L)
                        .name("Pizza Margherita")
                        .description(" Pizza Margherita  is a typical Neapolitan pizza, roundish in shape with a raised edge " +
                                " and garnished with hand-crushed peeled tomatoes, mozzarella " +
                                ", fresh basil leaves, and extra virgin olive oil.")
                        .price(12)
                        .build()
                );
                var osso_Buco = dishRepository.save(Dish.builder()
                        .id(3L)
                        .name("osso_Buco")
                        .description(" Ossobuco or osso buco is a specialty of Lombard cuisine of cross-cut veal shanks" +
                                " braised with vegetables, white wine, and broth")
                        .price(12)
                        .build()
                );

                //Asian
                var sushi = dishRepository.save(Dish.builder()
                        .id(4L)
                        .name("Sushi")
                        .description(" Sushi is a Japanese dish of prepared vinegared rice , usually with some sugar and salt" +
                                ", plus a variety of ingredients (ねた, neta), such as vegetables" +
                                ", and any meat, but most commonly seafood")
                        .price(32)
                        .build()
                );
                var dim_Sum = dishRepository.save(Dish.builder()
                        .id(5L)
                        .name("Dim Sum")
                        .description(" Dim sum  is a large range of small Chinese dishes " +
                                "that are traditionally enjoyed in restaurants for brunch.")
                        .price(32)
                        .build()
                );
                var kimchi = dishRepository.save(Dish.builder()
                        .id(6L)
                        .name("Kimchi")
                        .description(" Kimchi is a traditional Korean banchan consisting of salted and fermented vegetables" +
                                ", most commonly using napa cabbage or Korean radish")
                        .price(23)
                        .build()
                );
                //Mediterranean
                var moussaka = dishRepository.save(Dish.builder()
                        .id(7L)
                        .name("Moussaka")
                        .description(" Moussaka  is an eggplant (aubergine)- or potato-based dish, often including ground meat" +
                                ", which is common in the Balkans and the Middle East, with many local " +
                                "and regional variations.")
                        .price(23)
                        .build()
                );
                var tiramisu = dishRepository.save(Dish.builder()
                        .id(8L)
                        .name("Tiramisu")
                        .description(" Tiramisu is an Italian dessert made of ladyfinger pastries (savoiardi) " +
                                "dipped in coffee, layered with a whipped mixture of eggs" +
                                ", sugar and mascarpone and flavoured with cocoa.")
                        .price(46)
                        .build()
                );
                var paella = dishRepository.save(Dish.builder()
                        .id(9L)
                        .name("Paella")
                        .description("Paella is a rice dish originally from the Valencian Community." +
                                " Paella is regarded as one of the community's identifying symbols." +
                                "It is one of the best-known dishes in Spanish cuisine.")
                        .price(46)
                        .build()
                );
                //Eastern
                var hummus = dishRepository.save(Dish.builder()
                        .id(10L)
                        .name("Hummus")
                        .description(" Hummus is a Middle Eastern dip, spread, or savory dish made from cooked" +
                                ", mashed chickpeas blended with tahini, lemon juice, and garlic." +
                                " The standard garnish in the Middle East includes olive oil, a few whole chickpeas" +
                                ", parsley, and paprika.[4][5]")
                        .price(46)
                        .build()
                );
                var kebab = dishRepository.save(Dish.builder()
                        .id(11L)
                        .name("Kebab")
                        .description("Kebab is roasted meat that originates from the Middle East and has been popularised by Azerbaijani," +
                                " Arabic and Turkish cuisine. Many variants of the category are popular around the world" +
                                ", including the skewered shish kebab and the doner kebab with bread.")
                        .price(33)
                        .build()
                );
                var fesenjan = dishRepository.save(Dish.builder()
                        .id(12L)
                        .name("fesenjan")
                        .description(" fesenjan is a sweet and sour Iranian stew (a khoresh) from Northern Iran." +
                                " It is typically served over rice in the Iranian manner.")
                        .price(38)
                        .build()
                );

                if (kitchenKindRepository.count() == 0) {
                    var asian = kitchenKindRepository.save(Kitchen_Kind.builder()
                            .id(1L)
                            .name("Asian")
                            .description("asian  is a kitchen kind coming from japan & china.")
                            .dishes(Set.of(sushi, dim_Sum, kimchi))
                            .build()
                    );

                    var eastern = kitchenKindRepository.save(Kitchen_Kind.builder()
                            .id(2L)
                            .name("Eastern")
                            .description("Eastern  is a kitchen kind coming from the middle east .")
                            .dishes(Set.of(hummus, kebab, fesenjan))
                            .build()
                    );
                    var western = kitchenKindRepository.save(Kitchen_Kind.builder()
                            .id(3L)
                            .name("Western")
                            .description("Western  is a kitchen kind coming from Europe and North America.")
                            .dishes(Set.of(spaghetti_Carbonara
                                    , pizza_Margherita,
                                    osso_Buco))
                            .build()
                    );
                    var mediterranean = kitchenKindRepository.save(Kitchen_Kind.builder()
                            .id(4L)
                            .name("Mediterranean")
                            .description("Mediterranean  is a kitchen kind Found in countries bordering the Mediterranean Sea," +
                                    " such as Italy, Greece, and Spain.")
                            .dishes(Set.of(moussaka, tiramisu, paella))
                            .build()
                    );


                    chefRepository.save(Chef.builder()
                            .id(1L)
                            .name("Michal")
                            .img("https://tinyurl.com/mwpx5x4t")
                            .description("Michael is an asian chef")
                            .deliveryCost("10$-30$")
                            .food_Kosher("Not Kosher")
                            .kitchen_kinds(Set.of(asian))
                            .numOfOrders(0)
                            .available_dishes(Set.of(sushi, dim_Sum))
                            .avgPrice( (sushi.getPrice()
                                    + dim_Sum.getPrice())
                                    /
                                    2F
                            )
                            .residence("China,beijin")
                            .build()

                    );

                    chefRepository.save(Chef.builder()
                            .id(2L)
                            .name("Loren")
                            .img("https://tinyurl.com/yj5tn532")
                            .description("Loren is an Western chef")
                            .deliveryCost("0$-15$")
                            .food_Kosher(" Kosher")
                            .kitchen_kinds(Set.of(western))
                            .numOfOrders(22)
                            .available_dishes(Set.of(spaghetti_Carbonara, pizza_Margherita, osso_Buco))
                            .avgPrice( (spaghetti_Carbonara.getPrice()
                                    + pizza_Margherita.getPrice()
                                    +osso_Buco.getPrice())
                                    /
                                    3F
                            )
                            .residence("Israel,Tel aviv")
                            .build());

                    chefRepository.save(Chef.builder()
                            .id(3L)
                            .name("Milo")
                            .img("https://tinyurl.com/bp6k62y3")
                            .description("Milo is an Eastern chef")
                            .deliveryCost("5$-15$")
                            .food_Kosher(" Not Kosher")
                            .kitchen_kinds(Set.of(eastern))
                            .numOfOrders(22)
                            .available_dishes(Set.of(hummus, kebab))
                            .avgPrice( (hummus.getPrice()
                                    + kebab.getPrice()
                                   )
                                    /
                                   2F
                            )
                            .residence("Israel,Tel aviv")
                            .build());

                }

            }
        }



    }

}