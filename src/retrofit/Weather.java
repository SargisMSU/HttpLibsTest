package retrofit;

import java.util.List;

/** {
        {"coord":{"lon":44.51,"lat":40.18},
        "weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02n"}],
        "base":"stations",
        "main":{"temp":293.15,"pressure":1010,"humidity":40,"temp_min":293.15,"temp_max":293.15},
        "visibility":10000,"wind":{"speed":3.1,"deg":160},"clouds":{"all":20},
        "dt":1558284979,
        "sys":{"type":1,"id":8851,"message":0.0057,"country":"AM","sunrise":1558230201,"sunset":1558282423},
        "id":616052,
        "name":"Yerevan",
        "cod":200}
 */

public class Weather {
    private Coord coord;
    private int cod;
    private String base;
    private String name;
    private Main main;
    private List<WeatherDetails> weather;
    private Sys sys;

    public Weather(Coord coord, int cod, String base, String name, Main main, List<WeatherDetails> weather, Sys sys) {
        this.coord = coord;
        this.cod = cod;
        this.base = base;
        this.name = name;
        this.main = main;
        this.weather = weather;
        this.sys = sys;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int code) {
        this.cod = code;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    class Main {
        double temp;
        double pressure;
        int humidity;

        public Main(double temp, int pressure, int humidity) {
            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    '}';
        }
    }

    class Coord {
        double lat;
        double lon;

        public Coord(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        @Override
        public String toString() {
            return "Coord{" +
                    "lat=" + lat +
                    ", lon=" + lon +
                    '}';
        }
    }

    class WeatherDetails {
        private int id;
        String main;
        String description;
        String icon;

        public WeatherDetails() {
        }

        public WeatherDetails(int id, String main, String description, String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        @Override
        public String toString() {
            return "WeatherDetails{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    class Sys {
        int type;
        int id;
        String country;
        long sunrise;
        long sunset;

        public Sys() {
        }

        public Sys(int type, int id, String country, long sunrise, long sunset) {
            this.type = type;
            this.id = id;
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getSunrise() {
            return sunrise;
        }

        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public void setSunset(long sunset) {
            this.sunset = sunset;
        }

        @Override
        public String toString() {
            return "Sys{" +
                    "type=" + type +
                    ", id=" + id +
                    ", country='" + country + '\'' +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    '}';
        }
    }

    // Accesor methods
    public double getLat() {
        return coord.getLat();
    }

    public double getLon() {
        return coord.getLon();
    }

    public double getPressure() {
        return main.getPressure();
    }

    public double getTemp() {
        return main.getTemp();
    }

    public String getCountry() {
        return sys.getCountry();
    }

    public long getSunrise() {
        return sys.getSunrise();
    }

    public long getSunset() {
        return sys.getSunset();
    }

    @Override
    public String toString() {
        return "Weather{" +
                "coord=" + coord +
                ", cod=" + cod +
                ", base='" + base + '\'' +
                ", name='" + name + '\'' +
                ", main=" + main +
                ", weather=" + weather +
                ", sys=" + sys +
                '}';
    }
}