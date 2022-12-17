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
            return checkPlayerCollision((Player) otherObject);
        }

        if (otherObject instanceof Enemy) {
            return checkEnemyCollision((Enemy) otherObject);
        }

        return false;
    }

    private boolean checkEnemyCollision(Enemy enemy) {
        ImageBound enemyBoundaries = new ImageBound(enemy.x, enemy.y, enemy.width, enemy.height);
        ObjectBound obstacleBoundaries = new ObjectBound(x, y, width, height);

        if (enemy.getVelocityX() != 0) {
            if (obstacleBoundaries.topBoundary == enemyBoundaries.topBoundary && enemyBoundaries.bottomBoundary == obstacleBoundaries.bottomBoundary) {
                // Left collision
                if (enemyBoundaries.rightBoundary - obstacleBoundaries.rightBoundary > 0) {
                    return (enemyBoundaries.leftBoundary < obstacleBoundaries.rightBoundary);
                    // Right collision
                } else if (enemyBoundaries.leftBoundary - obstacleBoundaries.leftBoundary < 0) {
                    return (enemyBoundaries.rightBoundary > obstacleBoundaries.leftBoundary);
                }
            }
        }

        if (enemy.getVelocityY() != 0) {
            if (enemyBoundaries.leftBoundary == obstacleBoundaries.leftBoundary && enemyBoundaries.rightBoundary == obstacleBoundaries.rightBoundary) {
                // Top collision
                if (enemyBoundaries.topBoundary - obstacleBoundaries.topBoundary > 0) {
                    return (enemyBoundaries.topBoundary < obstacleBoundaries.bottomBoundary);
                    // Bottom collision
                } else if (enemyBoundaries.bottomBoundary - obstacleBoundaries.bottomBoundary < 0) {
                    return (enemyBoundaries.bottomBoundary > obstacleBoundaries.topBoundary);
                }
            }
        }

        return false;
    }

    private boolean checkPlayerCollision(Player player) {
        ImageBound playerBoundaries = new ImageBound(player.x, player.y, player.width, player.height);
        ObjectBound obstacleBoundaries = new ObjectBound(x, y, width, height);

        if ((obstacleBoundaries.topBoundary < playerBoundaries.topBoundary && playerBoundaries.topBoundary < obstacleBoundaries.bottomBoundary) || (obstacleBoundaries.topBoundary < playerBoundaries.bottomBoundary && playerBoundaries.bottomBoundary < obstacleBoundaries.bottomBoundary)) {
            // Left collision
            if (playerBoundaries.rightBoundary - obstacleBoundaries.rightBoundary > 0) {
                return (playerBoundaries.leftBoundary < obstacleBoundaries.rightBoundary);
                // Right collision
            } else if (playerBoundaries.topBoundary - obstacleBoundaries.topBoundary > 0) {
                return (playerBoundaries.rightBoundary > obstacleBoundaries.leftBoundary);
            }
        }

        if ((obstacleBoundaries.leftBoundary < playerBoundaries.leftBoundary && playerBoundaries.leftBoundary < obstacleBoundaries.rightBoundary) || (obstacleBoundaries.leftBoundary < playerBoundaries.rightBoundary && playerBoundaries.rightBoundary < obstacleBoundaries.rightBoundary)) {
            // Top Collision
            if (playerBoundaries.topBoundary - obstacleBoundaries.topBoundary > 0) {
                return (playerBoundaries.topBoundary < obstacleBoundaries.bottomBoundary);
                // Bottom Collision
            } else if (playerBoundaries.bottomBoundary - obstacleBoundaries.bottomBoundary < 0) {
                return (playerBoundaries.bottomBoundary > obstacleBoundaries.topBoundary);
            }
        }

        return false;
    }
}
