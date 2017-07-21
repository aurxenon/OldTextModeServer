package net.edcubed.SmittyCommons;

import java.io.Serializable;

public class Entity implements Serializable{
    private int[] coords = new int[2];
    private int moveSpeed = 1;
    private boolean playerControlled = false;
    private int attackDamage;
    private int organismHealth;
    private int maxHealth;

    public Entity(int[] xy) {
        this.coords = xy;
    }

    //coords
    public int[] getCoords() {return coords;}

    //speeds
    public int getMoveSpeed() {return this.moveSpeed;}
    public void setMoveSpeed(int speed) {this.moveSpeed = speed;}

    //movement
    public void moveRight() {this.coords[0] = this.coords[0] + this.moveSpeed;}
    public void moveLeft() {this.coords[0] = this.coords[0] - this.moveSpeed;}
    public void moveUp() {this.coords[1] = this.coords[1] - this.moveSpeed;}
    public void moveDown() {this.coords[1] = this.coords[1] + this.moveSpeed;}

    //controllable by players or not
    public boolean isControllable() {return playerControlled;}
    public void setControllable(boolean controllable) {this.playerControlled = controllable;}

    //health
    public int getAttackDamage() {return this.attackDamage;}
    public void setAttackDamage(int attack) {this.attackDamage = attack;}
    public int getHealth() {return this.organismHealth;}
    public void setHealth(int health) {this.organismHealth = health;}
    public void doDamage(int damage) {this.organismHealth = this.organismHealth + damage;} //negatives do damage, positives add health
    public int getMaxHealth() {return this.maxHealth;}
    public void setMaxHealth(int health) {this.maxHealth = health;}
}