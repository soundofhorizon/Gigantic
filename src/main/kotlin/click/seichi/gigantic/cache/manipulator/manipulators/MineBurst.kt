package click.seichi.gigantic.cache.manipulator.manipulators

import click.seichi.gigantic.cache.cache.Cache
import click.seichi.gigantic.cache.cache.PlayerCache
import click.seichi.gigantic.cache.manipulator.Manipulator
import click.seichi.gigantic.skill.Skills
import click.seichi.gigantic.skill.timer.LingeringSkillTimer

/**
 * @author tar0ss
 */
class MineBurst : LingeringSkillTimer(Skills.MINE_BURST), Manipulator<MineBurst, PlayerCache> {

    override fun from(cache: Cache<PlayerCache>): MineBurst? {
        return this
    }

    override fun set(cache: Cache<PlayerCache>): Boolean {
        return true
    }
}