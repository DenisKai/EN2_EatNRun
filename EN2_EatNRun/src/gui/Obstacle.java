package src.gui;

import gui.Window;

public class Obstacle extends GameObjectBase {
    public Obstacle(int x, int y) {
        super(x, y);
    }

    public void drawObstacle(Window window) {
        super.drawGeometry(window);
    }

    @Override
    public boolean intersects(GameObjectBase otherObject) {
        if (otherObject instanceof Player) {
            return checkPlayerCollision(otherObject);
        }

        if (otherObject instanceof Enemy) {
            return checkEnemyCollision((Enemy) otherObject);
        }

        return false;
    }

    private boolean checkEnemyCollision(Enemy enemy) {
        final int enemyLeft = enemy.x - enemy.width / 2;
        final int enemyRight = enemy.x + enemy.width / 2;
        final int enemyTop = enemy.y - enemy.height / 2;
        final int enemyBottom = enemy.y + enemy.height / 2;

        final int obstacleLeft = this.x;
        final int obstacleRight = this.x + width;
        final int obstacleTop = this.y;
        final int obstacleBottom = this.y + height;

        if (enemy.getVelocityX() != 0) {
            if (obstacleTop == enemyTop && enemyBottom == obstacleBottom) {
                // Left collision
                if (enemyRight - obstacleRight > 0) {
                    return (enemyLeft < obstacleRight);
                    // Right collision
                } else if (enemyLeft - obstacleLeft < 0) {
                    return (enemyRight > obstacleLeft);
                }
            }
        }

        if (enemy.getVelocityY() != 0) {
            if (enemyLeft == obstacleLeft && enemyRight == obstacleRight) {
                // Top collision
                if (enemyTop - obstacleTop > 0) {
                    return (enemyTop < obstacleBottom);
                    // Bottom collision
                } else if (enemyBottom - obstacleBottom < 0) {
                    return (enemyBottom > obstacleTop);
                }
            }
        }

        return false;
    }

    private boolean checkPlayerCollision(GameObjectBase otherObject) {
        final int playerLeft = otherObject.x - otherObject.width / 2;
        final int playerRight = otherObject.x + otherObject.width / 2;
        final int playerTop = otherObject.y - otherObject.height / 2;
        final int playerBottom = otherObject.y + otherObject.height / 2;

        final int obstacleLeft = this.x;
        final int obstacleRight = this.x + width;
        final int obstacleTop = this.y;
        final int obstacleBottom = this.y + height;

        if ((obstacleTop < playerTop && playerTop < obstacleBottom) || (obstacleTop < playerBottom && playerBottom < obstacleBottom)) {
            // Left collision
            if (playerRight - obstacleRight > 0) {
                return (playerLeft < obstacleRight);
                // Right collision
            } else if (playerTop - obstacleTop > 0) {
                return (playerRight > obstacleLeft);
            }
        }

        if ((obstacleLeft < playerLeft && playerLeft < obstacleRight) || (obstacleLeft < playerRight && playerRight < obstacleRight)) {
            // Top Collision
            if (playerTop - obstacleTop > 0) {
                return (playerTop < obstacleBottom);
                // Bottom Collision
            } else if (playerBottom - obstacleBottom < 0) {
                return (playerBottom > obstacleTop);
            }
        }

        return false;
    }
}
