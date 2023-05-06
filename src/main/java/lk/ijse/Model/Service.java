package lk.ijse.Model;

import javafx.scene.image.ImageView;
import lk.ijse.dto.Piece;
import lk.ijse.dto.Winner;

import java.util.List;

public class Service {
    public static int[] getClickedCellIndex(List<ImageView> imageViews, ImageView imageView) {
        if(imageView == imageViews.get(0)){
            return new int[]{0,0};
        }else if(imageView == imageViews.get(1)){
            return new int[]{0,1};
        }else if(imageView == imageViews.get(2)){
            return new int[]{0,2};
        }else if(imageView == imageViews.get(3)){
            return new int[]{1,0};
        }else if(imageView == imageViews.get(4)){
            return new int[]{1,1};
        }else if(imageView == imageViews.get(5)){
            return new int[]{1,2};
        }else if(imageView == imageViews.get(6)){
            return new int[]{2,0};
        }else if(imageView == imageViews.get(7)){
            return new int[]{2,1};
        }else if(imageView == imageViews.get(8)){
            return new int[]{2,2};
        }
        return null;
    }

    public static ImageView getImageView(int col, int rw, List<ImageView> imageViews) {
        if(col == 0 && rw == 0){
            return imageViews.get(0);
        }else if(col == 0 && rw == 1){
            return imageViews.get(1);
        }else if(col == 0 && rw == 2){
            return imageViews.get(2);
        }else if(col == 1 && rw == 0){
            return imageViews.get(3);
        }else if(col == 1 && rw == 1){
            return imageViews.get(4);
        }else if(col == 1 && rw == 2){
            return imageViews.get(5);
        }else if(col == 2 && rw == 0){
            return imageViews.get(6);
        }else if(col == 2 && rw == 1){
            return imageViews.get(7);
        }else if(col == 2 && rw == 2){
            return imageViews.get(8);
        }

        return null;
    }
}
