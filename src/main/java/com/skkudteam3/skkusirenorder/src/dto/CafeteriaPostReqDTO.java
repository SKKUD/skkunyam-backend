package com.skkudteam3.skkusirenorder.src.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.skkudteam3.skkusirenorder.src.entity.WeekDays;
import com.skkudteam3.skkusirenorder.src.entity.Cafeteria;
import com.skkudteam3.skkusirenorder.src.entity.Campus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/*
    식당 등록 시 사용하는 DTO
 */
@Data
@NoArgsConstructor
public class CafeteriaPostReqDTO {

    private String name;
    private String description;
    private String location;

    private String contact;
    private String email;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime closeTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime openTime;

    private Campus campus;

    @JsonProperty("weekdays")
    private WeekDays weekDays;

    public Cafeteria toEntity(){
        return new Cafeteria(this.description, this.name, this.location, this.closeTime, this.openTime, this.weekDays, this.campus, this.contact, this.email);
    }

}
