package click.seichi.gigantic.animation

import click.seichi.gigantic.util.Random
import org.bukkit.Particle

/**
 * @author tar0ss
 */
object SkillAnimations {

    val MINE_BURST_ON_BREAK = Animation(1) { location, _ ->
        location.world.spawnParticle(Particle.SPELL_MOB, location, 10,
                Random.nextGaussian(0.0, 0.5),
                Random.nextGaussian(0.0, 0.5),
                Random.nextGaussian(0.0, 0.5)
        )
    }

    val FLASH_BEFORE = Animation(1) { location, _ ->
        location.world.spawnParticle(Particle.SPELL_INSTANT, location, 200,
                Random.nextGaussian(0.0, 0.5),
                Random.nextGaussian(0.0, 0.5),
                Random.nextGaussian(0.0, 0.5)
        )

    }

    val TERRA_DRAIN_TREE = Animation(1) { location, _ ->
        location.world.spawnParticle(Particle.VILLAGER_HAPPY, location, 1,
                Random.nextGaussian(0.0, 0.3),
                Random.nextGaussian(0.0, 0.3),
                Random.nextGaussian(0.0, 0.3)
        )
    }

    val TERRA_DRAIN_HEAL = Animation(1) { location, _ ->
        location.world.spawnParticle(Particle.HEART, location, 10,
                Random.nextGaussian(0.0, 0.3),
                Random.nextGaussian(0.0, 0.3),
                Random.nextGaussian(0.0, 0.3)
        )
    }

    val HEAL = Animation(1) { location, _ ->
        location.world.spawnParticle(Particle.HEART, location, 3,
                Random.nextGaussian(0.0, 0.3),
                Random.nextGaussian(0.0, 0.3),
                Random.nextGaussian(0.0, 0.3)
        )
    }

}