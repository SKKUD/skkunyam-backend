package com.skkudteam3.skkusirenorder.src.dto;

import com.skkudteam3.skkusirenorder.src.entity.Menu;
import com.skkudteam3.skkusirenorder.src.entity.MenuOptionDetail;
import com.skkudteam3.skkusirenorder.src.entity.MenuOptionGroup;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*
    메뉴 등록 시 사용하는 DTO
 */
@Data
@NoArgsConstructor
public class MenuPostReqDTO {

    private Long cafeteriaId;
    private String name;
    private int price;
    private String category;
    private Boolean isSeason;
    private List<MenuOptionGroupDTO> menuOptionGroupDTOS = new ArrayList<>();

    public Menu toEntity(){
        List<MenuOptionGroup> menuOptionGroups = menuOptionGroupDTOS.stream().map(MenuOptionGroupDTO::toEntity).toList();
        return new Menu(name, price, category, isSeason, menuOptionGroups);
    }

    @Data
    @NoArgsConstructor
    public static class MenuOptionGroupDTO{
        private String name;
        private Boolean isEssentialOption;
        private List<MenuOptionDetailDTO> menuOptionDetailDTOs = new ArrayList<>();

        public MenuOptionGroupDTO(String name, Boolean isEssentialOption, List<MenuOptionDetailDTO> menuOptionDetailDTOs) {
            this.name = name;
            this.isEssentialOption = isEssentialOption;
            this.menuOptionDetailDTOs = menuOptionDetailDTOs;
        }

        public MenuOptionGroup toEntity(){
            List<MenuOptionDetail> menuOptionDetails = menuOptionDetailDTOs.stream().map(MenuOptionDetailDTO::toEntity).toList();
            return new MenuOptionGroup(name, isEssentialOption, menuOptionDetails);
        }
    }

    @Data
    @NoArgsConstructor
    public static class MenuOptionDetailDTO {
        private String name;
        private int price;

        public MenuOptionDetailDTO(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public MenuOptionDetail toEntity(){
            return new MenuOptionDetail(name, price);
        }
    }
}
